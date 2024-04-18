using System.Collections.Generic;

namespace WebappDb
{
    public partial class CommunicationProtocols
    {
        public CommunicationProtocols()
        {
            Sensors = new HashSet<Sensors>();
        }

        public int CommunicationProtocolId { get; set; }
        public string ProtocolName { get; set; }

        public virtual ICollection<Sensors> Sensors { get; set; }
    }
}
