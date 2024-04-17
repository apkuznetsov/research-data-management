using System.Collections.Generic;

namespace WebappDb
{
    public partial class Datatypes
    {
        public Datatypes()
        {
            Sensors = new HashSet<Sensors>();
        }

        public int DataTypeId { get; set; }
        public string Metadata { get; set; }
        public string Schema { get; set; }

        public virtual ICollection<Sensors> Sensors { get; set; }
    }
}
