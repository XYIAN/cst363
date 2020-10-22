CREATE TRIGGER gtrigger before insert on takes call "dw.AuditGradeTrigger";
CREATE TRIGGER gtriggerx before update on takes call "dw.AuditGradeTrigger";

