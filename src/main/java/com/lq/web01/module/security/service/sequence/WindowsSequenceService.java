package com.lq.web01.module.security.service.sequence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class WindowsSequenceService extends AbstractSequenceService {

	@Override
	public String getSequence() {
		String cpuID = getCPUSerial();
		String hdID = getHDSerial("C");
		if (cpuID == null || hdID == null) {
			return null;
		}
		String machineCode = getMD5(cpuID + hdID);

		return machineCode;
	}

	private String getHDSerial(String drive) {
		 StringBuilder result = new StringBuilder();
	        try {
	            File file = File.createTempFile("tmp", ".vbs");
	            file.deleteOnExit();
	            try (FileWriter fw = new java.io.FileWriter(file)) {
	                String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
	                        + "Set colDrives = objFSO.Drives\n" + "Set objDrive = colDrives.item(\"" + drive + "\")\n"
	                        + "Wscript.Echo objDrive.SerialNumber";
	                fw.write(vbs);
	            }
	            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
	            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
	                String line;
	                while ((line = input.readLine()) != null) {
	                    result.append(line);
	                }
	            }
	            file.delete();
	        } catch (Throwable e) {
	            LOG.error("生成HDSerial失败", e);
	        }
	        if (result.length() < 1) {
	            LOG.info("无磁盘ID被读取");
	        }

	        return result.toString();
	}

	private String getCPUSerial() {
		StringBuilder result = new StringBuilder();
		try {
			File file = File.createTempFile("tmp", ".vbs");
			file.deleteOnExit();
			try (FileWriter fw = new FileWriter(file)) {
				String vbs = "On Error Resume Next \r\n\r\n"
						+ "strComputer = \".\"  \r\n"
						+ "Set objWMIService = GetObject(\"winmgmts:\" _ \r\n"
						+ "    & \"{impersonationLevel=impersonate}!\\\\\" & strComputer & \"\\root\\cimv2\") \r\n"
						+ "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_Processor\")  \r\n "
						+ "For Each objItem in colItems\r\n "
						+ "    Wscript.Echo objItem.ProcessorId  \r\n "
						+ "    exit for  ' do the first cpu only! \r\n"
						+ "Next                    ";

				fw.write(vbs);
			}
			Process p = Runtime.getRuntime().exec(
					"cscript //NoLogo " + file.getPath());
			try (BufferedReader input = new BufferedReader(
					new InputStreamReader(p.getInputStream()))) {
				String line;
				while ((line = input.readLine()) != null) {
					result.append(line);
				}
			}
			file.delete();
		} catch (Throwable e) {
			LOG.error("生成CPUSerial失败", e);
		}
		if (result.length() < 1) {
			LOG.info("无CPU_ID被读取");
		}
		return result.toString();
	}

}
