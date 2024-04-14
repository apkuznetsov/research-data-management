namespace WebappDb
{
    public partial class ExperimentSensors
    {
        public int ExperimentSensorId { get; set; }
        public int ExperimentId { get; set; }
        public int SensorId { get; set; }

        public virtual Experiments Experiment { get; set; }
        public virtual Sensors Sensor { get; set; }
    }
}
