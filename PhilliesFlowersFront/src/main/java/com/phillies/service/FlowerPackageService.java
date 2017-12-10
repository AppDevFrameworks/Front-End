package com.phillies.service;

import java.util.List;

import com.phillies.domain.FlowerPackage;

public interface FlowerPackageService {
	public FlowerPackage getPackage(String name);
	public List<FlowerPackage> getPackages();
	public void updatePackageStock(String pack);
	public String[] getFlowers(String pack);	
}