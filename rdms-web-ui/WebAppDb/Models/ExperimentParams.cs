namespace WebappDb
{
    public partial class ExperimentParams
    {
        public int ExperimentParamId { get; set; }
        public int ExperimentId { get; set; }
        public int MetadataParameterId { get; set; }

        public virtual Experiments Experiment { get; set; }
        public virtual MetadataParameters MetadataParameter { get; set; }
    }
}
