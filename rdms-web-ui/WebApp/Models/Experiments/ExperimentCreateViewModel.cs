using System.ComponentModel.DataAnnotations;

namespace Webapp.Models.Experiments
{
    public class ExperimentCreateViewModel
    {
        public int ExperimentId { get; set; }

        [Display(Name = "Название"),
            Required(ErrorMessage = "Введите название эксперимента")]
        public string Name { get; set; }

        [Display(Name = "Описание"),
            Required(ErrorMessage = "Введите описание эксперимента")]
        public string Metadata { get; set; }

        [Display(Name = "Датчик"),
            Required(ErrorMessage = "Выберите датчик")]
        public int SensorId { get; set; }

    }
}
