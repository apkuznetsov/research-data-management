using System.ComponentModel.DataAnnotations;

namespace Webapp.Models
{
    public class SensorDetailsViewModel
    {
        public int SensorId { get; set; }

        [Display(Name = "Название")]
        public string Name { get; set; }

        [Display(Name = "Описание")]
        public string Metadata { get; set; }

        [Display(Name = "JSON-схема фиксируемых данных")]
        public string DatatypeScheme { get; set; }

        [Display(Name = "Сетевой протокол")]
        public string CommunicationProtocolName { get; set; }

        [Display(Name = "IP-адрес")]
        public string IpAddress { get; set; }

        [Display(Name = "Порт")]
        public int Port { get; set; }
    }
}
