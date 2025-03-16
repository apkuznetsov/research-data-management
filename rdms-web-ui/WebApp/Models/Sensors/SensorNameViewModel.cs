using System.ComponentModel.DataAnnotations;

namespace Webapp.Models
{
    public class SensorNameViewModel
    {
        public int SensorId { get; set; }

        [Display(Name = "Название")]
        public string Name { get; set; }
    }
}
