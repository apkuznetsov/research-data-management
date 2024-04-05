using System.Collections.Generic;

namespace WebappDb
{
    public partial class Tags
    {
        public Tags()
        {
            ExperimentTags = new HashSet<ExperimentTags>();
        }

        public int TagId { get; set; }
        public string Value { get; set; }

        public virtual ICollection<ExperimentTags> ExperimentTags { get; set; }
    }
}
