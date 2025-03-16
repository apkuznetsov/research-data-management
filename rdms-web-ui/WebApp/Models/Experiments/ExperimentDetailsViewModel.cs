using System;
using System.ComponentModel.DataAnnotations;

namespace Webapp.Models.Experiments
{
    public class ExperimentDetailsViewModel
    {
        public int ExperimentId { get; set; }

        [Display(Name = "Название")]
        public string Name { get; set; }

        [Display(Name = "Описание")]
        public string Metadata { get; set; }

        [Display(Name = "Дата создания")]
        public DateTime CreatedAt { get; set; }

        public int ExperimentSensorId { get; set; }

        [Display(Name = "Датчик")]
        public string ExperimentSensorName { get; set; }
    }
}
