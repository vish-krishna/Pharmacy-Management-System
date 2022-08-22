package com.pharmacy.suppliermanagement;

import com.pharmacy.suppliermanagement.models.Supplier;
import com.pharmacy.suppliermanagement.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.pharmacy.suppliermanagement.dao.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
class SupplierManagementApplicationTests {

	@MockBean
	private SupplierRepository supplierRepository;
	@Autowired
	private SupplierService supplierService;
	@Test
	public void getSupplierTest() {
		when(supplierRepository.findAll()).thenReturn(Stream
				.of(new Supplier(1,"name1","email1",123))
				.collect(Collectors.toList()));
		assertEquals(1,supplierRepository.findAll().size());
	}

	@Test
	public void getSupplierByIdTest() {
		long id=1;
		Supplier supplier = new Supplier(1,"name1","email1",123);
		when(supplierRepository.findSupplierBySupplierId(id)).thenReturn(supplier);
		assertEquals(id,supplier.getSupplierId());
	}

	@Test
	public void createSupplierTest() {
		Supplier supplier = new Supplier(1,"name1","email1",1);
		when(supplierRepository.save(supplier)).thenReturn(supplier);
		assertEquals(supplier,supplierRepository.save(supplier));
	}

	@Test
	public void deleteSupplierTest() {
		Supplier supplier = new Supplier(1,"name1","email1",1);
		supplierRepository.delete(supplier);
		verify(supplierRepository,times(1)).delete(supplier);
	}

}
