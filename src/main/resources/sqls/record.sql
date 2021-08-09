create table if not exists record(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    system_name TEXT NULL,
    task_detail TEXT NULL,
    task_date TEXT NULL,
    create_time TEXT NULL DEFAULT(datetime('now','+8 hours')),
    is_deleted integer not null default(0)
)


alter table record add period TEXT null;