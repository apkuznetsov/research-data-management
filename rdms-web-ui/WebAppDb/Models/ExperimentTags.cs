namespace WebappDb
{
    public partial class ExperimentTags
    {
        public int ExperimentTagId { get; set; }
        public int ExperimentId { get; set; }
        public int TagId { get; set; }

        public virtual Experiments Experiment { get; set; }
        public virtual Tags Tag { get; set; }
    }
}
