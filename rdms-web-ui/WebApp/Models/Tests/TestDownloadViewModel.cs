using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Webapp.Models.Tests
{
    public class TestDownloadViewModel
    {
        public int TestId { get; set; }

        public DateTime StartedTime { get; set; }

        public DateTime? EndedTime { get; set; }

        public int ExperimentSensorId { get; set; }
    }
}
