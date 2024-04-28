using System;

namespace Webapp.Helpers
{
    public static class CommandBuilder
    {
        public static string BuildSensorListenerStartCommand(
            int testId, 
            string sensorIpAddress, int sensorPort, 
            int durationSeconds)
        {
            return $"-testId {testId} -executionTime {durationSeconds} -sensors {sensorIpAddress}:{sensorPort}";
        }

        public static string BuildSensorOutputParserStartCommand(
            int testId, 
            DateTime leftTimeBorder, DateTime rightTimeBorder, 
            string sensorIpAddress, int sensorPort, 
            string dirPath)
        {
            string dateTimePattern = "yyyy-MM-ddTHH:mm:ssZ";

            return $"-directoryPath \"{dirPath}\" " +
                $"-leftTimeBorder {leftTimeBorder.ToString(dateTimePattern)} " +
                $"-rightTimeBorder {rightTimeBorder.ToString(dateTimePattern)} " +
                $"-testId {testId} " +
                $"-sensors {sensorIpAddress}:{sensorPort}";
        }
    }
}
