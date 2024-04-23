using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Internal;
using System;
using System.Linq;
using System.Threading.Tasks;
using Webapp.Models;
using Webapp.Models.Experiments;
using WebappDb;

namespace Webapp.Controllers
{
    public class ExperimentsController : Controller
    {
        private readonly WebappdbContext db;

        public ExperimentsController(WebappdbContext context)
        {
            db = context;
        }

        // GET: Experiments
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Globalization", "CA1305:Укажите IFormatProvider", Justification = "<Ожидание>")]
        public IActionResult Index()
        {
            // SELECT "ExperimentId", "Name" FROM experiments WHERE "ExperimentId" = Any (SELECT "ExperimentId" FROM user_experiments WHERE "UserId" = 122);
            var userExperimentsNames = db.Experiments.
                Where(e => db.UserExperiments.Any(ue => ue.UserId == GetCurrUserId() && ue.ExperimentId == e.ExperimentId)).
                Select(e => new ExperimentNameViewModel { ExperimentId = e.ExperimentId, Name = e.Name }).ToList();

            return View(userExperimentsNames);
        }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Globalization", "CA1305:Укажите IFormatProvider", Justification = "<Ожидание>")]
        private int GetCurrUserId()
        {
            return Convert.ToInt32(User.Claims.FirstOrDefault(x => x.Type == "UserId")?.Value);
        }

        // GET: Experiments/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            ExperimentDetailsViewModel experimentVm = await db.Experiments.Select(m =>
                new ExperimentDetailsViewModel
                {
                    ExperimentId = m.ExperimentId,
                    Name = m.Name,
                    Metadata = m.Metadata,
                    CreatedAt = m.CreatedAt
                }).FirstOrDefaultAsync(m => m.ExperimentId == id).ConfigureAwait(true);

            if (experimentVm == null)
            {
                return NotFound();
            }

            experimentVm.ExperimentSensorId = db.ExperimentSensors.FirstOrDefault(m => m.ExperimentId == experimentVm.ExperimentId).SensorId;
            experimentVm.ExperimentSensorName = db.Sensors.FirstOrDefault(m => m.SensorId == experimentVm.ExperimentSensorId).Name;

            return View(experimentVm);
        }

        // GET: Experiments/Create
        public IActionResult Create()
        {
            ViewData["SensorId"] = new SelectList(
                db.Sensors,
                "SensorId",
                "Name");

            return View();
        }

        // POST: Experiments/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Design", "CA1062:Проверить аргументы или открытые методы", Justification = "<Ожидание>")]
        public async Task<IActionResult> Create([Bind("Name,Metadata,SensorId")] ExperimentCreateViewModel experimentVm)
        {
            if (ModelState.IsValid)
            {
                Experiments experiment = new Experiments
                {
                    Name = experimentVm.Name,
                    Metadata = experimentVm.Metadata,
                    CreatedAt = DateTime.Now
                };

                db.Add(experiment);
                await db.SaveChangesAsync().ConfigureAwait(true);

                ExperimentSensors experimentSensor = new ExperimentSensors
                {
                    ExperimentId = experiment.ExperimentId,
                    SensorId = experimentVm.SensorId
                };

                db.Add(experimentSensor);
                await db.SaveChangesAsync().ConfigureAwait(true);

                UserExperiments userExperiment = new UserExperiments
                {
                    UserId = GetCurrUserId(),
                    ExperimentId = experiment.ExperimentId
                };

                db.Add(userExperiment);
                await db.SaveChangesAsync().ConfigureAwait(true);

                return RedirectToAction(nameof(Index));
            }

            ViewData["SensorId"] = new SelectList(
                db.Sensors,
                "SensorId",
                "Name",
                experimentVm.SensorId);

            return View(experimentVm);
        }

        // GET: Experiments/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            ExperimentEditViewModel experimentVm = await db.Experiments.Select(m =>
            new ExperimentEditViewModel
            {
                ExperimentId = m.ExperimentId,
                Name = m.Name,
                Metadata = m.Metadata
            }).FirstOrDefaultAsync(m => m.ExperimentId == id).ConfigureAwait(true);

            if (experimentVm == null)
            {
                return NotFound();
            }

            experimentVm.ExperimentSensorId = db.ExperimentSensors.FirstOrDefault(m => m.ExperimentId == experimentVm.ExperimentId).SensorId;
            experimentVm.ExperimentSensorName = db.Sensors.FirstOrDefault(m => m.SensorId == experimentVm.ExperimentSensorId).Name;

            return View(experimentVm);
        }

        // POST: Experiments/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Design", "CA1062:Проверить аргументы или открытые методы", Justification = "<Ожидание>")]
        public async Task<IActionResult> Edit(int id, [Bind("ExperimentId,Name,Metadata")] Experiments experiment)
        {
            if (id != experiment.ExperimentId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    db.Update(experiment);
                    await db.SaveChangesAsync().ConfigureAwait(true);
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!ExperimentsExists(experiment.ExperimentId))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }

            return View(experiment);
        }

        // GET: Experiments/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            ExperimentDeleteViewModel experimentVm = await db.Experiments.Select(m =>
                new ExperimentDeleteViewModel
                {
                    ExperimentId = m.ExperimentId,
                    Name = m.Name,
                    Metadata = m.Metadata,
                    CreatedAt = m.CreatedAt
                }).FirstOrDefaultAsync(m => m.ExperimentId == id).ConfigureAwait(true);

            if (experimentVm == null)
            {
                return NotFound();
            }

            experimentVm.ExperimentSensorId = db.ExperimentSensors.FirstOrDefault(m => m.ExperimentId == experimentVm.ExperimentId).SensorId;
            experimentVm.ExperimentSensorName = db.Sensors.FirstOrDefault(m => m.SensorId == experimentVm.ExperimentSensorId).Name;

            return View(experimentVm);
        }

        // POST: Experiments/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var userExperiment = db.UserExperiments.Where(m => m.ExperimentId == id);
            db.UserExperiments.RemoveRange(userExperiment);
            await db.SaveChangesAsync().ConfigureAwait(true);

            var experimentSensor = db.ExperimentSensors.Where(m => m.ExperimentId == id);
            db.ExperimentSensors.RemoveRange(experimentSensor);
            await db.SaveChangesAsync().ConfigureAwait(true);

            var experiment = await db.Experiments.FindAsync(id).ConfigureAwait(true);
            db.Experiments.Remove(experiment);
            await db.SaveChangesAsync().ConfigureAwait(true);

            return RedirectToAction(nameof(Index));
        }

        private bool ExperimentsExists(int id)
        {
            return db.Experiments.Any(e => e.ExperimentId == id);
        }
    }
}
