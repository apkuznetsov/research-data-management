using System.ComponentModel.DataAnnotations;

namespace Webapp.Models
{
    public class RegisterViewModel
    {
        [Required(ErrorMessage = "Не указана эл. почта")]
        public string Email { get; set; }

        [Required(ErrorMessage = "Не указан пароль")]
        [DataType(DataType.Password)]
        public string Password { get; set; }

        [DataType(DataType.Password)]
        [Compare("Password", ErrorMessage = "Пароль введён неверно")]
        public string ConfirmPassword { get; set; }

        [Display(Name = "Фамилия")]
        public string Surname { get; set; }

        [Display(Name = "Имя")]
        public string Forename { get; set; }
    }
}
