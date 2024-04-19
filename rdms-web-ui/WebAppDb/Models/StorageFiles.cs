using System.Collections.Generic;

namespace WebappDb
{
    public partial class StorageFiles
    {
        public StorageFiles()
        {
            ProcessedData = new HashSet<ProcessedData>();
            TestStorageFiles = new HashSet<TestStorageFiles>();
        }

        public int StorageFileId { get; set; }
        public string Uri { get; set; }
        public string Description { get; set; }

        public virtual ICollection<ProcessedData> ProcessedData { get; set; }
        public virtual ICollection<TestStorageFiles> TestStorageFiles { get; set; }
    }
}
