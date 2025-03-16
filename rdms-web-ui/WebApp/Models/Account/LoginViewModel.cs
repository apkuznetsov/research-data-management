using System.ComponentModel.DataAnnotations;

namespace Webapp.Models
{
    public class LoginViewModel
    {
        [Required(ErrorMessage = "Не указана эл. почта")]
        public string Email { get; set; }

        [Required(ErrorMessage = "Не указан пароль")]
        [DataType(DataType.Password)]
        public string Password { get; set; }
    }
}
