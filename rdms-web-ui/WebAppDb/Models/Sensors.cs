using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebappDb
{
    public partial class Sensors
    {
        public Sensors()
        {
            ExperimentSensors = new HashSet<ExperimentSensors>();
            ProcessingSensors = new HashSet<ProcessingSensors>();
        }

        public int SensorId { get; set; }

        [Display(Name = "Название")]
        public string Name { get; set; }

        [Display(Name = "Описание")]
        public string Metadata { get; set; }

        [Display(Name = "JSON-схема фиксируемых данных")]
        public int DataTypeId { get; set; }

        [Display(Name = "Сетевой протокол")]
        public int CommunicationProtocolId { get; set; }

        [Display(Name = "IP-адрес")]
        public string IpAddress { get; set; }

        [Display(Name = "Порт")]
        public int Port { get; set; }

        public virtual Datatypes DataType { get; set; }
        public virtual CommunicationProtocols CommunicationProtocol { get; set; }
        public virtual ICollection<ExperimentSensors> ExperimentSensors { get; set; }
        public virtual ICollection<ProcessingSensors> ProcessingSensors { get; set; }
    }
}
