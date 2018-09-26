package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import com.apap.tutorial3.PilotModel;
import org.springframework.stereotype.Service;

@Service
public class PilotInMemoryService implements PilotService{
	private List<PilotModel> archivePilot;
	
	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		archivePilot.add(pilot);
	}

	@Override
	public List<PilotModel> getPilotList() {
		return archivePilot;
	}

	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		PilotModel pilot = null;
		for (PilotModel pilots : archivePilot) {
			if (pilots.getLicenseNumber().equals(licenseNumber)){
				pilot = pilots;
			}
		}
		return pilot;
	}

	@Override
	public PilotModel deletePilotDetailById(String id) {
		PilotModel pilot = null;
		for (PilotModel pilots : archivePilot) {
			if (pilots.getId().equals(id)){
				pilot = pilots;
				archivePilot.remove(pilots);
			}
		}
		return pilot;
	}

}
