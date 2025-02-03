package com.andrea.neuralnetimagerecognition.ml;

import android.content.Context;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Object;
import java.util.HashMap;
import java.util.Map;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.metadata.MetadataExtractor;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/**
 * This model doesn't have metadata, so no javadoc can be generated. */
public final class Model {
  @NonNull
  private final org.tensorflow.lite.support.model.Model model;

  private Model(@NonNull Context context,
      @NonNull org.tensorflow.lite.support.model.Model.Options options) throws IOException {
    model = org.tensorflow.lite.support.model.Model.createModel(context, "model.tflite", options);
    MetadataExtractor extractor = new MetadataExtractor(model.getData());
  }

  @NonNull
  public static Model newInstance(@NonNull Context context) throws IOException {
    return new Model(context, (new org.tensorflow.lite.support.model.Model.Options.Builder()).build());
  }

  @NonNull
  public static Model newInstance(@NonNull Context context,
      @NonNull org.tensorflow.lite.support.model.Model.Options options) throws IOException {
    return new Model(context, options);
  }

  @NonNull
  public Outputs process(@NonNull TensorBuffer inputFeature0) {
    TensorBuffer processedinputFeature0 = inputFeature0;
    Outputs outputs = new Outputs(model);
    model.run(new Object[] {processedinputFeature0.getBuffer()}, outputs.getBuffer());
    return outputs;
  }

  public void close() {
    model.close();
  }

  public class Outputs {
    private TensorBuffer outputFeature0;

    private Outputs(org.tensorflow.lite.support.model.Model model) {
      this.outputFeature0 = TensorBuffer.createFixedSize(model.getOutputTensorShape(0), DataType.FLOAT32);
    }

    @NonNull
    public TensorBuffer getOutputFeature0AsTensorBuffer() {
      return outputFeature0;
    }

    @NonNull
    private Map<Integer, Object> getBuffer() {
      Map<Integer, Object> outputs = new HashMap<>();
      outputs.put(0, outputFeature0.getBuffer());
      return outputs;
    }
  }
}
