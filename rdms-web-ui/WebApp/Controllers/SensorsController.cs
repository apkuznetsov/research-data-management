using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using System.Linq;
using System.Threading.Tasks;
using Webapp.Models;
using Webapp.Models.Sensors;
using WebappDb;

namespace Webapp.Controllers
{
    public class SensorsController : Controller
    {
        private readonly WebappdbContext _context;

        public SensorsController(WebappdbContext context)
        {
            _context = context;
        }

        // GET: Sensors
        public IActionResult Index()
        {
            var sensorNames = _context.Sensors.Select(m =>
            new SensorNameViewModel
            {
                SensorId = m.SensorId,
                Name = m.Name
            }).ToList();

            return View(sensorNames);
        }

        // GET: Sensors/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            SensorDetailsViewModel sensorVm = await _context.Sensors.Select(m =>
                new SensorDetailsViewModel
                {
                    SensorId = m.SensorId,
                    Name = m.Name,
                    Metadata = m.Metadata,
                    DatatypeScheme = m.DataType.Schema,
                    CommunicationProtocolName = m.CommunicationProtocol.ProtocolName,
                    IpAddress = m.IpAddress,
                    Port = m.Port
                }).FirstOrDefaultAsync(m => m.SensorId == id).ConfigureAwait(true);

            if (sensorVm == null)
            {
                return NotFound();
            }

            return View(sensorVm);
        }

        // GET: Sensors/Create
        public IActionResult Create()
        {
            ViewData["CommunicationProtocolId"] = new SelectList(
                _context.CommunicationProtocols,
                "CommunicationProtocolId",
                "ProtocolName");

            return View();
        }

        // POST: Sensors/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Design", "CA1062:Проверить аргументы или открытые методы", Justification = "<Ожидание>")]
        public async Task<IActionResult> Create([Bind("SensorId,Name,Metadata,DataType,CommunicationProtocolId,IpAddress,Port")] SensorCreateViewModel sensorVm)
        {
            if (ModelState.IsValid)
            {
                Datatypes newDatatype = new Datatypes
                {
                    Schema = sensorVm.DataType
                };

                _context.Add(newDatatype);
                await _context.SaveChangesAsync().ConfigureAwait(true);

                Sensors newSensor = new Sensors
                {
                    SensorId = sensorVm.SensorId,
                    Name = sensorVm.Name,
                    Metadata = sensorVm.Metadata,
                    CommunicationProtocolId = sensorVm.CommunicationProtocolId,
                    IpAddress = sensorVm.IpAddress,
                    Port = sensorVm.Port,
                    DataTypeId = newDatatype.DataTypeId
                };

                _context.Add(newSensor);
                await _context.SaveChangesAsync().ConfigureAwait(true);

                return RedirectToAction(nameof(Index));
            }

            ViewData["CommunicationProtocolId"] = new SelectList(
                _context.CommunicationProtocols,
                "CommunicationProtocolId",
                "ProtocolName",
                sensorVm.CommunicationProtocolId);

            return View(sensorVm);
        }

        // GET: Sensors/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            SensorEditViewModel sensor = await _context.Sensors.Select(m =>
                new SensorEditViewModel
                {
                    SensorId = m.SensorId,
                    Name = m.Name,
                    Metadata = m.Metadata,
                    DataTypeId = m.DataType.DataTypeId,
                    DatatypeScheme = m.DataType.Schema,
                    CommunicationProtocolId = m.CommunicationProtocolId,
                    IpAddress = m.IpAddress,
                    Port = m.Port
                }).FirstOrDefaultAsync(m => m.SensorId == id).ConfigureAwait(true);

            if (sensor == null)
            {
                return NotFound();
            }

            ViewData["CommunicationProtocolId"] = new SelectList(_context.CommunicationProtocols, "CommunicationProtocolId", "ProtocolName", sensor.CommunicationProtocolId);

            return View(sensor);
        }

        // POST: Sensors/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Design", "CA1062:Проверить аргументы или открытые методы", Justification = "<Ожидание>")]
        public async Task<IActionResult> Edit(int id, [Bind("SensorId,Name,Metadata,IpAddress,Port,CommunicationProtocolId")] Sensors sensor)
        {
            if (id != sensor.SensorId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(sensor);
                    await _context.SaveChangesAsync().ConfigureAwait(true);
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!SensorsExists(sensor.SensorId))
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

            ViewData["CommunicationProtocolId"] = new SelectList(_context.CommunicationProtocols, "CommunicationProtocolId", "ProtocolName", sensor.CommunicationProtocolId);

            return View(sensor);
        }

        // GET: Sensors/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            SensorDeleteViewModel sensorVm = await _context.Sensors.Select(m =>
                new SensorDeleteViewModel
                {
                    SensorId = m.SensorId,
                    Name = m.Name,
                    Metadata = m.Metadata,
                    DatatypeScheme = m.DataType.Schema,
                    CommunicationProtocolName = m.CommunicationProtocol.ProtocolName,
                    IpAddress = m.IpAddress,
                    Port = m.Port
                }).FirstOrDefaultAsync(m => m.SensorId == id).ConfigureAwait(true);

            if (sensorVm == null)
            {
                return NotFound();
            }

            return View(sensorVm);
        }

        // POST: Sensors/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var sensors = await _context.Sensors.FindAsync(id).ConfigureAwait(true);
            _context.Sensors.Remove(sensors);
            await _context.SaveChangesAsync().ConfigureAwait(true);

            return RedirectToAction(nameof(Index));
        }

        private bool SensorsExists(int id)
        {
            return _context.Sensors.Any(e => e.SensorId == id);
        }
    }
}
