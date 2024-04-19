using System;
using System.Collections.Generic;

namespace WebappDb
{
    public partial class Processings
    {
        public Processings()
        {
            ProcessedData = new HashSet<ProcessedData>();
            ProcessingSensors = new HashSet<ProcessingSensors>();
            ProcessingTests = new HashSet<ProcessingTests>();
        }

        public int ProcessingId { get; set; }
        public string Metadata { get; set; }
        public DateTimeOffset StartTimeBorder { get; set; }
        public DateTimeOffset EndTimeBorder { get; set; }
        public string Name { get; set; }

        public virtual ICollection<ProcessedData> ProcessedData { get; set; }
        public virtual ICollection<ProcessingSensors> ProcessingSensors { get; set; }
        public virtual ICollection<ProcessingTests> ProcessingTests { get; set; }
    }
}
