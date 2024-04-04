namespace WebappDb
{
    public partial class TestParams
    {
        public int TestParamId { get; set; }
        public int TestId { get; set; }
        public int MetadataParameterId { get; set; }

        public virtual MetadataParameters MetadataParameter { get; set; }
        public virtual Tests Test { get; set; }
    }
}
