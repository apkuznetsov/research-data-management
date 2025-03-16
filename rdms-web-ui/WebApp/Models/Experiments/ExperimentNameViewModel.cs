using System.ComponentModel.DataAnnotations;

namespace Webapp.Models
{
    public class ExperimentNameViewModel
    {
        public int ExperimentId { get; set; }

        [Display(Name = "Название")]
        public string Name { get; set; }
    }
}
