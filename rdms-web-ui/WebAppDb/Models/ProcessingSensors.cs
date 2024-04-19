namespace WebappDb
{
    public partial class ProcessingSensors
    {
        public int ProcessingSensorId { get; set; }
        public int ProcessingId { get; set; }
        public int SensorId { get; set; }

        public virtual Processings Processing { get; set; }
        public virtual Sensors Sensor { get; set; }
    }
}
