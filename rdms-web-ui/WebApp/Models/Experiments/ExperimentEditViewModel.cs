using System.ComponentModel.DataAnnotations;

namespace Webapp.Models.Experiments
{
    public class ExperimentEditViewModel
    {
        public int ExperimentId { get; set; }

        [Display(Name = "Название")]
        public string Name { get; set; }

        [Display(Name = "Описание")]
        public string Metadata { get; set; }

        public int ExperimentSensorId { get; set; }

        [Display(Name = "Датчик")]
        public string ExperimentSensorName { get; set; }
    }
}
