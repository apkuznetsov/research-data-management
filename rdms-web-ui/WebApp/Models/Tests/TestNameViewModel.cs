using System.ComponentModel.DataAnnotations;

namespace Webapp.Models
{
    public class TestNameViewModel
    {
        public int TestId { get; set; }

        [Display(Name = "Название")]
        public string Name { get; set; }
    }
}
