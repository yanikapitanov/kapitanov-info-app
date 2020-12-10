package com.kapitanovslog.dailyinfoapp.services.reminder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
interface ReminderRepository extends CrudRepository<ReminderEntity, Long> {

    List<ReminderEntity> findByReminderAt(LocalDate reminderAt);
}
