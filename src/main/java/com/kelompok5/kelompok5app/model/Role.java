package com.romanceramic.mrp.model;

public enum Role {
    ADMIN("Administrator"),
    WAREHOUSE("Departemen Gudang"),
    PPIC("Production Planning and Inventory Control"),
    PURCHASING("Purchasing"),
    USER("Pemakai Barang");
    
    private final String displayName;
    
    Role(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
