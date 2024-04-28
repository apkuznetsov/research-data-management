using Microsoft.EntityFrameworkCore;

namespace WebappDb
{
    public partial class WebappdbContext : DbContext
    {
        public WebappdbContext()
        {
        }

        public WebappdbContext(DbContextOptions<WebappdbContext> options)
            : base(options)
        {
        }

        public virtual DbSet<CommunicationProtocols> CommunicationProtocols { get; set; }
        public virtual DbSet<Datatypes> Datatypes { get; set; }
        public virtual DbSet<ExperimentParams> ExperimentParams { get; set; }
        public virtual DbSet<ExperimentSensors> ExperimentSensors { get; set; }
        public virtual DbSet<ExperimentTags> ExperimentTags { get; set; }
        public virtual DbSet<Experiments> Experiments { get; set; }
        public virtual DbSet<MetadataParameters> MetadataParameters { get; set; }
        public virtual DbSet<ProcessedData> ProcessedData { get; set; }
        public virtual DbSet<ProcessingSensors> ProcessingSensors { get; set; }
        public virtual DbSet<ProcessingTests> ProcessingTests { get; set; }
        public virtual DbSet<Processings> Processings { get; set; }
        public virtual DbSet<Sensors> Sensors { get; set; }
        public virtual DbSet<StorageFiles> StorageFiles { get; set; }
        public virtual DbSet<Tags> Tags { get; set; }
        public virtual DbSet<TestParams> TestParams { get; set; }
        public virtual DbSet<TestStorageFiles> TestStorageFiles { get; set; }
        public virtual DbSet<Tests> Tests { get; set; }
        public virtual DbSet<UserExperiments> UserExperiments { get; set; }
        public virtual DbSet<Users> Users { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseNpgsql("Host=localhost;Port=5432;Database=webappdb;Username=postgres;Password=password");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<CommunicationProtocols>(entity =>
            {
                entity.HasKey(e => e.CommunicationProtocolId)
                    .HasName("PK_CommunicationProtocols");

                entity.ToTable("communication_protocols");

                entity.Property(e => e.ProtocolName)
                    .IsRequired()
                    .HasMaxLength(20);
            });

            modelBuilder.Entity<Datatypes>(entity =>
            {
                entity.HasKey(e => e.DataTypeId)
                    .HasName("PK_Datatypes");

                entity.ToTable("datatypes");

                entity.Property(e => e.Metadata).IsRequired();

                entity.Property(e => e.Schema)
                    .IsRequired()
                    .HasColumnType("json");
            });

            modelBuilder.Entity<ExperimentParams>(entity =>
            {
                entity.HasKey(e => e.ExperimentParamId)
                    .HasName("PK_ExperimentParams");

                entity.ToTable("experiment_params");

                entity.HasOne(d => d.Experiment)
                    .WithMany(p => p.ExperimentParams)
                    .HasForeignKey(d => d.ExperimentId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ExperimentParam_ExperimentId_Experiment");

                entity.HasOne(d => d.MetadataParameter)
                    .WithMany(p => p.ExperimentParams)
                    .HasForeignKey(d => d.MetadataParameterId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ExperimentParam_MetadataParameterId_MetadataParameter");
            });

            modelBuilder.Entity<ExperimentSensors>(entity =>
            {
                entity.HasKey(e => e.ExperimentSensorId)
                    .HasName("PK_ExperimentSensors");

                entity.ToTable("experiment_sensors");

                entity.HasOne(d => d.Experiment)
                    .WithMany(p => p.ExperimentSensors)
                    .HasForeignKey(d => d.ExperimentId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ExperimentSensor_ExperimentId_Experiment");

                entity.HasOne(d => d.Sensor)
                    .WithMany(p => p.ExperimentSensors)
                    .HasForeignKey(d => d.SensorId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ExperimentSensor_SensorId_Sensor");
            });

            modelBuilder.Entity<ExperimentTags>(entity =>
            {
                entity.HasKey(e => e.ExperimentTagId)
                    .HasName("PK_ExperimentTags");

                entity.ToTable("experiment_tags");

                entity.HasOne(d => d.Experiment)
                    .WithMany(p => p.ExperimentTags)
                    .HasForeignKey(d => d.ExperimentId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ExperimentTag_ExperimentId_Experiment");

                entity.HasOne(d => d.Tag)
                    .WithMany(p => p.ExperimentTags)
                    .HasForeignKey(d => d.TagId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ExperimentTag_TadId_Tag");
            });

            modelBuilder.Entity<Experiments>(entity =>
            {
                entity.HasKey(e => e.ExperimentId)
                    .HasName("PK_Experiments");

                entity.ToTable("experiments");

                entity.HasIndex(e => e.Name)
                    .HasName("constraint_unique_experiment_name")
                    .IsUnique();

                entity.Property(e => e.CreatedAt).HasColumnType("timestamp with time zone");

                entity.Property(e => e.Metadata).IsRequired();

                entity.Property(e => e.Name).IsRequired();
            });

            modelBuilder.Entity<MetadataParameters>(entity =>
            {
                entity.HasKey(e => e.MetadataParameterId)
                    .HasName("PK_MetadataParameters");

                entity.ToTable("metadata_parameters");

                entity.Property(e => e.Name).IsRequired();

                entity.Property(e => e.Value).IsRequired();
            });

            modelBuilder.Entity<ProcessedData>(entity =>
            {
                entity.ToTable("processed_data");

                entity.HasOne(d => d.Processing)
                    .WithMany(p => p.ProcessedData)
                    .HasForeignKey(d => d.ProcessingId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ProcessedData_ProcessingsId_Processing");

                entity.HasOne(d => d.StorageFile)
                    .WithMany(p => p.ProcessedData)
                    .HasForeignKey(d => d.StorageFileId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ProcessedData_StorageFileId_StorageFile");
            });

            modelBuilder.Entity<ProcessingSensors>(entity =>
            {
                entity.HasKey(e => e.ProcessingSensorId)
                    .HasName("PK_ProcessingsSensors");

                entity.ToTable("processing_sensors");

                entity.HasOne(d => d.Processing)
                    .WithMany(p => p.ProcessingSensors)
                    .HasForeignKey(d => d.ProcessingId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ProcessingSensor_ProcessingId_Processing");

                entity.HasOne(d => d.Sensor)
                    .WithMany(p => p.ProcessingSensors)
                    .HasForeignKey(d => d.SensorId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ProcessingSensor_SensorId_Sensor");
            });

            modelBuilder.Entity<ProcessingTests>(entity =>
            {
                entity.HasKey(e => e.ProcessingTestId)
                    .HasName("PK_ProcessingTests");

                entity.ToTable("processing_tests");

                entity.HasOne(d => d.Processing)
                    .WithMany(p => p.ProcessingTests)
                    .HasForeignKey(d => d.ProcessingId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ProcessingTest_ProcessingId_Processing");

                entity.HasOne(d => d.Test)
                    .WithMany(p => p.ProcessingTests)
                    .HasForeignKey(d => d.TestId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ProcessingTest_TestId_Test");
            });

            modelBuilder.Entity<Processings>(entity =>
            {
                entity.HasKey(e => e.ProcessingId)
                    .HasName("PK_Processings");

                entity.ToTable("processings");

                entity.HasIndex(e => e.Name)
                    .HasName("constraint_unique_processing_name")
                    .IsUnique();

                entity.Property(e => e.EndTimeBorder).HasColumnType("time with time zone");

                entity.Property(e => e.Metadata).IsRequired();

                entity.Property(e => e.Name).IsRequired();

                entity.Property(e => e.StartTimeBorder).HasColumnType("time with time zone");
            });

            modelBuilder.Entity<Sensors>(entity =>
            {
                entity.HasKey(e => e.SensorId)
                    .HasName("PK_Sensors");

                entity.ToTable("sensors");

                entity.Property(e => e.IpAddress)
                    .IsRequired()
                    .HasMaxLength(50);

                entity.Property(e => e.Metadata).IsRequired();

                entity.HasOne(d => d.CommunicationProtocol)
                    .WithMany(p => p.Sensors)
                    .HasForeignKey(d => d.CommunicationProtocolId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Sensor_CommunicationProtocolId_CommunicationProtocol");

                entity.HasOne(d => d.DataType)
                    .WithMany(p => p.Sensors)
                    .HasForeignKey(d => d.DataTypeId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Sensor_DataTypeId_Datatype");

                entity.Property(e => e.Name).IsRequired();
            });

            modelBuilder.Entity<StorageFiles>(entity =>
            {
                entity.HasKey(e => e.StorageFileId)
                    .HasName("PK_StorageFiles");

                entity.ToTable("storage_files");

                entity.Property(e => e.Description).IsRequired();

                entity.Property(e => e.Uri)
                    .IsRequired()
                    .HasColumnName("URI");
            });

            modelBuilder.Entity<Tags>(entity =>
            {
                entity.HasKey(e => e.TagId)
                    .HasName("PK_Tags");

                entity.ToTable("tags");

                entity.Property(e => e.Value).IsRequired();
            });

            modelBuilder.Entity<TestParams>(entity =>
            {
                entity.HasKey(e => e.TestParamId)
                    .HasName("PK_TestParams");

                entity.ToTable("test_params");

                entity.HasOne(d => d.MetadataParameter)
                    .WithMany(p => p.TestParams)
                    .HasForeignKey(d => d.MetadataParameterId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_TestParam_MetadataParameterId_MetadataParameter");

                entity.HasOne(d => d.Test)
                    .WithMany(p => p.TestParams)
                    .HasForeignKey(d => d.TestId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_TestParam_TestId_Test");
            });

            modelBuilder.Entity<TestStorageFiles>(entity =>
            {
                entity.HasKey(e => e.TestStorageFileId)
                    .HasName("PK_TestStorageFiles");

                entity.ToTable("test_storage_files");

                entity.HasOne(d => d.StorageFile)
                    .WithMany(p => p.TestStorageFiles)
                    .HasForeignKey(d => d.StorageFileId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_TestStorageFile_StorageFileId_StorageFile");

                entity.HasOne(d => d.Test)
                    .WithMany(p => p.TestStorageFiles)
                    .HasForeignKey(d => d.TestId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_TestStorageFile_TestId_Test");
            });

            modelBuilder.Entity<Tests>(entity =>
            {
                entity.HasKey(e => e.TestId)
                    .HasName("PK_Tests");

                entity.ToTable("tests");

                entity.HasIndex(e => e.Name)
                    .HasName("constraint_unique_test_name")
                    .IsUnique();

                entity.Property(e => e.EndedTime).HasColumnType("timestamp with time zone");

                entity.Property(e => e.Metadata).IsRequired();

                entity.Property(e => e.Name).IsRequired();

                entity.Property(e => e.StartedTime)
                    .HasColumnType("timestamp with time zone")
                    .HasDefaultValueSql("now()");

                entity.HasOne(d => d.Experiment)
                    .WithMany(p => p.Tests)
                    .HasForeignKey(d => d.ExperimentId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Test_ExperimentId_Experiment");
            });

            modelBuilder.Entity<UserExperiments>(entity =>
            {
                entity.ToTable("user_experiments");

                entity.Property(e => e.ExperimentId).ValueGeneratedOnAdd();

                entity.Property(e => e.UserId).ValueGeneratedOnAdd();

                entity.HasOne(d => d.Experiment)
                    .WithMany(p => p.UserExperiments)
                    .HasForeignKey(d => d.ExperimentId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_User_Experiments_ExperimentId");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.UserExperiments)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_User_Experiments_UserId");
            });

            modelBuilder.Entity<Users>(entity =>
            {
                entity.HasKey(e => e.UserId)
                    .HasName("PK_User");

                entity.ToTable("users");

                entity.HasIndex(e => e.Email)
                    .HasName("constraint_unique_user_email")
                    .IsUnique();

                entity.Property(e => e.Email).IsRequired();

                entity.Property(e => e.Forename).IsRequired();

                entity.Property(e => e.Password).IsRequired();

                entity.Property(e => e.Surname).IsRequired();
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
