package com.example.cryptocurrencywallet.repository;

import com.example.cryptocurrencywallet.settings.PlatformSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformSettingsRepository extends JpaRepository<PlatformSettings, Long> {
}
