using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebappDb
{
    public partial class Tests
    {
        public Tests()
        {
            ProcessingTests = new HashSet<ProcessingTests>();
            TestParams = new HashSet<TestParams>();
            TestStorageFiles = new HashSet<TestStorageFiles>();
        }

        public int TestId { get; set; }

        [Display(Name = "Название")]
        public string Name { get; set; }

        [Display(Name = "Описание")]
        public string Metadata { get; set; }

        [Display(Name = "Время начала")]
        public DateTime StartedTime { get; set; }

        [Display(Name = "Время завершения")]
        public DateTime? EndedTime { get; set; }

        public int ExperimentId { get; set; }

        public virtual Experiments Experiment { get; set; }
        public virtual ICollection<TestParams> TestParams { get; set; }
        public virtual ICollection<TestStorageFiles> TestStorageFiles { get; set; }
        public virtual ICollection<ProcessingTests> ProcessingTests { get; set; }
    }
}
