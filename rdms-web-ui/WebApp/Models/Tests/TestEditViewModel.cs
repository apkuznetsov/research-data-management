using System;
using System.ComponentModel.DataAnnotations;

namespace Webapp.Models.Tests
{
    public class TestEditViewModel
    {
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

        [Display(Name = "Эксперимент")]
        public string ExperimentName { get; set; }

        public int ExperimentSensorId { get; set; }

        [Display(Name = "Датчик")]
        public string ExperimentSensorName { get; set; }
    }
}
