using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebappDb
{
    public partial class Experiments
    {
        public Experiments()
        {
            ExperimentParams = new HashSet<ExperimentParams>();
            ExperimentSensors = new HashSet<ExperimentSensors>();
            ExperimentTags = new HashSet<ExperimentTags>();
            Tests = new HashSet<Tests>();
            UserExperiments = new HashSet<UserExperiments>();
        }

        public int ExperimentId { get; set; }

        [Display(Name = "Название")]
        public string Name { get; set; }

        [Display(Name = "Описание")]
        public string Metadata { get; set; }

        [Display(Name = "Дата создания")]
        public DateTime CreatedAt { get; set; }

        public virtual ICollection<ExperimentParams> ExperimentParams { get; set; }
        public virtual ICollection<ExperimentTags> ExperimentTags { get; set; }
        public virtual ICollection<ExperimentSensors> ExperimentSensors { get; set; }
        public virtual ICollection<Tests> Tests { get; set; }
        public virtual ICollection<UserExperiments> UserExperiments { get; set; }
    }
}
