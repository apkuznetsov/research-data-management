namespace WebappDb
{
    public partial class ProcessedData
    {
        public int ProcessedDataId { get; set; }
        public int ProcessingId { get; set; }
        public int StorageFileId { get; set; }

        public virtual Processings Processing { get; set; }
        public virtual StorageFiles StorageFile { get; set; }
    }
}
