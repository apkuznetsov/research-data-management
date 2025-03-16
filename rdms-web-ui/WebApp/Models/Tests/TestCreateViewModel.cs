using System;
using System.ComponentModel.DataAnnotations;

namespace Webapp.Models.Tests
{
    public class TestCreateViewModel
    {
        public int TestId { get; set; }

        [Display(Name = "Название"),
            Required(ErrorMessage = "Введите название измерения")]
        public string Name { get; set; }

        [Display(Name = "Описание"),
            Required(ErrorMessage = "Введите описание измерения")]
        public string Metadata { get; set; }

        public int ExperimentId { get; set; }

        [Display(Name = "Эксперимент")]
        public string ExperimentName { get; set; }

        public int ExperimentSensorId { get; set; }

        [Display(Name = "Датчик")]
        public string ExperimentSensorName { get; set; }

        [Display(Name = "Длительность измерения в секундах"),
            Required(ErrorMessage = "Введите длительность"),
            Range(1, 3600, ErrorMessage = "Максимальная длительность – 3600 секунд")]
        public int DurationSeconds { get; set; }

        [Display(Name = "Время начала")]
        public DateTime StartedTime { get; set; }

        [Display(Name = "Время завершения")]
        public DateTime? EndedTime { get; set; }
    }
}
