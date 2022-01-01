package com.github.draperaj.thelsamar.service

import com.github.draperaj.thelsamar.model.Inventory
import org.springframework.data.jpa.repository.JpaRepository

interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
