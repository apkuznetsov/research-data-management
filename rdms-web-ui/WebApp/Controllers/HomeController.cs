using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System.Diagnostics;
using Webapp.Models;

namespace Webapp.Controllers
{
    public class HomeController : Controller
    {
        public HomeController(ILogger<HomeController> logger)
        {
        }

        [Authorize]
        public IActionResult Index()
        {
            return RedirectToAction("Index", "Experiments");
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
