using Microsoft.AspNetCore.Mvc;
using System.Linq;
using Webapp.Models;
using WebappDb;

namespace Webapp.Controllers
{
    public class ProcessingsController : Controller
    {
        private readonly WebappdbContext db;

        public ProcessingsController(WebappdbContext context)
        {
            db = context;
        }

        // GET: Processings
        public IActionResult Index()
        {
            var processings = db.Processings.Select(m => new ProcessingNameViewModel { ProcessingId = m.ProcessingId, Name = m.Name }).ToList();

            if (processings == null)
            {
                return NotFound();
            }

            return View(processings);
        }
    }
}
