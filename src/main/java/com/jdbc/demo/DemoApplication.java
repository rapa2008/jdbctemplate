package com.jdbc.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//Rahul Pattar
//PoC Complete
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {

		log.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE IF EXISTS BALANCE");
		jdbcTemplate.execute("CREATE TABLE BALANCE(" +
				"id SERIAL AUTO_INCREMENT, balance VARCHAR(255), name VARCHAR(25), amount BIGINT, date TIMESTAMP)");

		// Split up the array of whole names into an array of first/last names
		List<String> splitUpBalances;
		splitUpBalances = Arrays.asList("8199", "435475", "23423", "323");//.stream()

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Use a Java 8 stream to print out each tuple of the list
		splitUpBalances.forEach(balance -> {log.info(String.format("Inserting balance(balance, name, amount, date) record for %s", balance));
			jdbcTemplate.update("INSERT INTO BALANCE(balance, name, amount, date) VALUES (?,?,?,?)", balance, "rahul",
					1000, timestamp);
		});

		log.info("Querying for BALANCE records where balance = '435475':");


        String sql = "SELECT * FROM BALANCE";
        List<BalanceRequest> balanceRequestList1 = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(BalanceRequest.class)
        );


        String sql_1 = "SELECT * FROM BALANCE WHERE " +
				"name = (?) AND balance IN (?,?)";
        int id = 1;

        List<BalanceRequest> balanceRequestList3 = jdbcTemplate.query(
                sql_1,
                new Object[]{"rahul", "8199", "323"},
                new BeanPropertyRowMapper<>(BalanceRequest.class));

		System.out.println("Values from select query");
        balanceRequestList3.forEach(e-> System.out.println(e.toString()));

		String updateSql = "UPDATE BALANCE SET name = ? WHERE id = ?";
		Object[] params = { "pattar", 4};
		int[] types = {Types.VARCHAR, Types.BIGINT};

		int rows = jdbcTemplate.update(updateSql, params, types);

		System.out.println(rows + " row(s) updated.");
	}

}
