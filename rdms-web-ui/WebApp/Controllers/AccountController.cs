using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Security.Claims;
using System.Threading.Tasks;
using Webapp.Models;
using WebappDb;

namespace Webapp.Controllers
{
    public class AccountController : Controller
    {
        private readonly WebappdbContext _context;

        public AccountController(WebappdbContext context)
        {
            _context = context;
        }

        public IActionResult Login()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Design", "CA1062:Проверить аргументы или открытые методы", Justification = "<Ожидание>")]
        public async Task<IActionResult> Login(LoginViewModel loginVm)
        {
            if (ModelState.IsValid)
            {
                Users user = await _context.Users.FirstOrDefaultAsync(
                    u => u.Email == loginVm.Email &&
                    u.Password == loginVm.Password).ConfigureAwait(true);

                if (user != null)
                {
                    await Authenticate(user.UserId, loginVm.Email).ConfigureAwait(true);

                    return RedirectToAction("Index", "Home");
                }
                ModelState.AddModelError("", "Некорректные эл. почта и/или пароль");
            }

            return View(loginVm);
        }

        public IActionResult Register()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Design", "CA1062:Проверить аргументы или открытые методы", Justification = "<Ожидание>")]
        public async Task<IActionResult> Register(RegisterViewModel registerVm)
        {
            if (ModelState.IsValid)
            {
                Users user = await _context.Users.FirstOrDefaultAsync(
                    u => u.Email == registerVm.Email).ConfigureAwait(true);

                if (user == null)
                {
                    Users newUser = new Users
                    {
                        Email = registerVm.Email,
                        Password = registerVm.Password,
                        Surname = registerVm.Surname,
                        Forename = registerVm.Forename
                    };

                    _context.Add(newUser);
                    await _context.SaveChangesAsync().ConfigureAwait(true);

                    await Authenticate(newUser.UserId, registerVm.Email).ConfigureAwait(true);

                    return RedirectToAction("Index", "Home");
                }
                else
                {
                    ModelState.AddModelError("", "Некорректные эл. почта и/или пароль");
                }
            }

            return View(registerVm);
        }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Globalization", "CA1305:Specify IFormatProvider", Justification = "<Pending>")]
        private async Task Authenticate(int userId, string userName)
        {
            var claims = new List<Claim>
            {
                new Claim("UserId", userId.ToString()),
                new Claim(ClaimsIdentity.DefaultNameClaimType, userName)
            };

            ClaimsIdentity id = new ClaimsIdentity(claims, "ApplicationCookie", ClaimsIdentity.DefaultNameClaimType, ClaimsIdentity.DefaultRoleClaimType);

            await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, new ClaimsPrincipal(id)).ConfigureAwait(true);
        }

        public async Task<IActionResult> Logout()
        {
            await HttpContext.SignOutAsync(CookieAuthenticationDefaults.AuthenticationScheme).ConfigureAwait(true);

            return RedirectToAction("Login", "Account");
        }
    }
}