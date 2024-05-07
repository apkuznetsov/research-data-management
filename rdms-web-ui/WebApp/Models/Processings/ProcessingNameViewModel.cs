using System.ComponentModel.DataAnnotations;

namespace Webapp.Models
{
    public class ProcessingNameViewModel
    {
        public int ProcessingId { get; set; }

        [Display(Name = "Название")]
        public string Name { get; set; }
    }
}
