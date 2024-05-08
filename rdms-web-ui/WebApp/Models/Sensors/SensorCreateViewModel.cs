using System.ComponentModel.DataAnnotations;

namespace Webapp.Models
{
    public class SensorCreateViewModel
    {
        public int SensorId { get; set; }

        [Display(Name = "Название")]
        public string Name { get; set; }

        [Display(Name = "Описание")]
        public string Metadata { get; set; }

        [Display(Name = "JSON-схема фиксируемых данных")]
        public string DataType { get; set; }

        [Display(Name = "Сетевой протокол")]
        public int CommunicationProtocolId { get; set; }

        [Display(Name = "IP-адрес")]
        public string IpAddress { get; set; }

        [Display(Name = "Порт")]
        public int Port { get; set; }
    }
}
