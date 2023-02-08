package com.task.permission.model;

public enum Role {
    /**
     * Spring security tarafından belirli end pointlerin filtrelenmesini sağlamak amacıyla
     * her bir kullanıcıya rol ataması sağlamak için
     * Çalışan ve Yönetici olmak üzere iki ayrı rol tanımı yapılmıştır.
     */
    ROLE_EMPLOYEE("ÇALIŞAN"),ROLE_MANAGER("YÖNETİCİ");

    private final String displayText;

    Role(String displayText) {
        this.displayText = displayText;
    }
    public String getDisplayText(){
        return displayText;
    }
}
