package com.navi.challenge.mymoney.exeutor;

import com.navi.challenge.mymoney.dao.DataStub;
import com.navi.challenge.mymoney.executor.MyMoneyExecutor;
import com.navi.challenge.mymoney.service.MyMoneyService;
import com.navi.challenge.mymoney.service.MyMoneyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.navi.challenge.mymoney.model.Asset.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyMoneyExecutorTest {
  @Mock private DataStub dataStub;
  @Mock private MyMoneyService myMoneyService;
  @Spy private MyMoneyExecutor myMoneyExecutor;

  @BeforeEach
  public void setUp() {
    dataStub = new DataStub();
    dataStub.defaultAssetOrderForIO.add(ASSET_EQUITY);
    dataStub.defaultAssetOrderForIO.add(ASSET_DEBT);
    dataStub.defaultAssetOrderForIO.add(ASSET_GOLD);
    myMoneyService = new MyMoneyServiceImpl(dataStub);
    myMoneyExecutor = new MyMoneyExecutor(myMoneyService);
  }

  @Test
  void testExecuteCommandsFromFileWithInvalidFile() {
    assertThrows(
        IOException.class,
        () -> myMoneyExecutor.executeCommandsFromFile("inputFile"),
        "Expected Allocate method to throw Exception, but it didn't.");
  }

  @Test
  void testExecuteCommandsFromFileWithValidFile() throws IOException {
    String inputFile =
        Objects.requireNonNull(this.getClass().getClassLoader().getResource("testInputFile1"))
            .getFile();
    String outputFile =
        Objects.requireNonNull(this.getClass().getClassLoader().getResource("testOutputFile1"))
            .getFile();
    List<String> output = myMoneyExecutor.executeCommandsFromFile(inputFile);
    try (Stream<String> lines = Files.lines(Paths.get(outputFile))) {
      String expectedResult = lines.map(String::trim).collect(Collectors.joining(";"));
      String result =
          output.stream()
              .filter(Objects::nonNull)
              .map(String::trim)
              .collect(Collectors.joining(";"));
      assertEquals(expectedResult, result);
    }
  }
}
