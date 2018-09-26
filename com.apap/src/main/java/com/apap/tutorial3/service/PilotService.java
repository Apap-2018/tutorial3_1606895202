package com.apap.tutorial3.service;

import java.util.List;
import com.apap.tutorial3.PilotModel;

public interface PilotService {
	void addPilot (PilotModel pilot);
	List<PilotModel> getPilotList();
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	PilotModel deletePilotDetailById(String pilot);
}
