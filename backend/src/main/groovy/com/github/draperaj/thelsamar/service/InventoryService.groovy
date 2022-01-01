package com.github.draperaj.thelsamar.service

import com.github.draperaj.thelsamar.model.Inventory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InventoryService {
  @Autowired
  private InventoryRepository inventoryRepository

  Inventory getItemById(Integer id) {
    def item = inventoryRepository.findById(id)
    return ( !item.empty ? item.get() : null )
  }
}
