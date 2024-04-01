namespace WebappDb
{
    public partial class UserExperiments
    {
        public int UserExperimentsId { get; set; }
        public int UserId { get; set; }
        public int ExperimentId { get; set; }

        public virtual Experiments Experiment { get; set; }
        public virtual Users User { get; set; }
    }
}
