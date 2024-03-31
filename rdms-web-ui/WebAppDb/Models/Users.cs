using System.Collections.Generic;

namespace WebappDb
{
    public partial class Users
    {
        public Users()
        {
            UserExperiments = new HashSet<UserExperiments>();
        }

        public int UserId { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public string Surname { get; set; }
        public string Forename { get; set; }

        public virtual ICollection<UserExperiments> UserExperiments { get; set; }
    }
}
