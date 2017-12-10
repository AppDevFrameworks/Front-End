package com.phillies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phillies.domain.Flower;
import com.phillies.domain.FlowerPackage;
import com.phillies.repository.FlowerPackageRepo;

@Service
public class FlowerPackageServiceImp implements FlowerPackageService {

	@Autowired
	private FlowerPackageRepo flowerPackageRepo;

	@Override
	public FlowerPackage getPackage(String name) {
		FlowerPackage pkg = null;
		List<FlowerPackage> pack = flowerPackageRepo.findAll();
		for(FlowerPackage p : pack) {
			if(p.getName().equals(name))
				pkg = p;
		}
		return pkg;
	}

	@Override
	public List<FlowerPackage> getPackages() {
		List<FlowerPackage> pack = flowerPackageRepo.findAll();
		return pack;
	}

	@Override
	public void updatePackageStock(String pack) {
		FlowerPackage flowerPackage = getPackage(pack);
		int stock = flowerPackage.getStock()-1;
		flowerPackage.setStock(stock);
		flowerPackageRepo.save(new FlowerPackage(flowerPackage.getId(), flowerPackage.getName(), flowerPackage.getFlowers(), flowerPackage.getItems(), flowerPackage.getStock(), flowerPackage.getPrice()));
	}

	@Override
	public String[] getFlowers(String pack) {
		FlowerPackage flowerPackage = getPackage(pack);
		String[] flowers = flowerPackage.getFlowers();
		return flowers;
	}

	@Override
	public String[] getItems(String pack) {
		FlowerPackage flowerPackage = getPackage(pack);
		String[] items = flowerPackage.getItems();
		return items;
	}
	
}