# 📘 Hibernate LMS Project – Complete README

This repository contains a **Learning Management System (LMS)** implemented using **Hibernate ORM with JPA annotations**.
---

## 🔹 Technology Stack

* Java
* Hibernate ORM
* JPA (Jakarta Persistence)
* MySQL / PostgreSQL
* Ehcache
* Maven

---

## 🔹 JPA ANNOTATIONS – FOUNDATIONS

### `@Entity`

Marks a class as a persistent entity.

```java
@Entity
public class User {}
```

---

### `@Table`

Maps the entity to a database table.

```java
@Entity
@Table(name = "users")
public class User {}
```

---

### `@Id` & `@GeneratedValue`

Defines the primary key and its generation strategy.

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

---

### `@Column`

Customizes column properties.

```java
@Column(nullable = false, unique = true)
private String email;
```

---

## 🔹 ENUM MAPPING

### Enum Definition

```java
public enum Role {
    USER, ADMIN;

    public String authority() {
        return "ROLE_" + name();
    }

    public String withoutPrefix() {
        return name();
    }
}
```

### Enum Usage

```java
@Enumerated(EnumType.STRING)
private Role role;
```

✅ `EnumType.STRING` is safe and recommended

---

## 🔹 VALUE TYPES (EMBEDDABLES)

### `@Embeddable`

Used for reusable value objects.

```java
@Embeddable
public class Address {
    private String city;
    private String state;
}
```

### `@Embedded` with Overrides

```java
@Embedded
@AttributeOverrides({
    @AttributeOverride(name = "city", column = @Column(name = "home_city")),
    @AttributeOverride(name = "state", column = @Column(name = "home_state"))
})
private Address homeAddress;
```

✅ No separate table created

---

## 🔹 ENTITY RELATIONSHIPS (VERY IMPORTANT)

### One-to-One Mapping (User ↔ UserProfile)

```java
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "profile_id")
private UserProfile profile;
```

* User is the owning side
* Foreign key stored in `users` table

---

### One-to-Many & Many-to-One

#### User → Payment

```java
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Payment> payments;
```

```java
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
```

---

#### Category → Course

```java
@OneToMany(mappedBy = "category")
private List<Course> courses;
```

---

#### CourseSection → Lesson

```java
@OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
private List<Lesson> lessons;
```

---

### Many-to-Many Mapping (User ↔ Course)

```java
@ManyToMany
@JoinTable(
    name = "enrollments",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
)
private Set<Course> courses;
```

#### Convenience Method

```java
public void enroll(Course course) {
    courses.add(course);
    course.getUsers().add(this);
}
```

---

## 🔹 FETCH TYPES & PERFORMANCE

```java
@OneToMany(fetch = FetchType.LAZY)
private List<Course> courses;
```

* Default and recommended
* Prevents unnecessary DB calls

---

## 🔹 INHERITANCE & POLYMORPHISM (ADVANCED)

### JOINED Strategy

```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public abstract class User {}
```

```java
@Entity
@DiscriminatorValue("FREE")
public class FreeUser extends User {}
```

```java
@Entity
@DiscriminatorValue("PREMIUM")
public class PremiumUser extends User {}
```

✅ Normalized schema

---

## 🔹 POLYMORPHIC QUERY

```java
List<User> users = session.createQuery("from User", User.class).list();
```

Returns FreeUser and PremiumUser objects.

---

## 🔹 HIBERNATE SESSION LIFECYCLE

* Transient
* Persistent
* Detached
* Removed

---

## 🔹 CRUD OPERATIONS

```java
session.persist(entity);
session.find(User.class, id);
session.merge(entity);
session.remove(entity);
```

---

## 🔹 TRANSACTION MANAGEMENT

```java
Transaction tx = session.beginTransaction();
session.persist(entity);
tx.commit();
```

Rollback happens automatically on exception.

---

## 🔹 CACHING (VERY ADVANCED)

### First Level Cache (L1)

* Session scoped
* Enabled by default

```java
session.find(User.class, 1L);
session.find(User.class, 1L);
```

---

### Second Level Cache (L2)

```java
@Cacheable
@org.hibernate.annotations.Cache(
    usage = CacheConcurrencyStrategy.READ_WRITE
)
public class Course {}
```

Uses Ehcache.

---

### Query Cache

```java
session.createQuery("from Course")
       .setCacheable(true)
       .list();
```

---

### Hibernate Statistics

```java
sessionFactory.getStatistics().getSecondLevelCacheHitCount();
```

---

## 🔹 HQL & QUERY OPTIMIZATION

### N+1 Problem

```java
from Course
```

❌ Causes multiple queries

### JOIN FETCH Solution

```java
select distinct c from Course c join fetch c.sections
```

✅ Single optimized query

---

##  DATABASE & CONFIGURATION

### Programmatic Configuration

```java
StandardServiceRegistry registry =
    new StandardServiceRegistryBuilder()
        .applySettings(properties)
        .build();
```

---

### Schema Generation

```properties
hibernate.hbm2ddl.auto=update
```

---

##  AUDITING & TIME HANDLING

```java
@Embeddable
public class AuditInfo {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
```

```java
@Embedded
private AuditInfo auditInfo;
```

---

##  DESIGN & BEST PRACTICES

* Proper owning vs inverse side
* Controlled cascade usage
* Orphan removal safety
* Lazy loading by default
* Bidirectional consistency methods
* Safe entity lifecycle management

---

