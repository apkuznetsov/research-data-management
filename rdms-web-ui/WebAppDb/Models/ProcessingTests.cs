namespace WebappDb
{
    public partial class ProcessingTests
    {
        public int ProcessingTestId { get; set; }
        public int ProcessingId { get; set; }
        public int TestId { get; set; }

        public virtual Processings Processing { get; set; }
        public virtual Tests Test { get; set; }
    }
}
